package com.ecom.backend.order.service;

import com.ecom.backend.cart.model.Cart;
import com.ecom.backend.cart.model.CartItem;
import com.ecom.backend.cart.repository.CartRepository;
import com.ecom.backend.order.dto.OrderDto;
import com.ecom.backend.order.model.Order;
import com.ecom.backend.order.model.OrderItem;
import com.ecom.backend.order.repository.OrderRepository;
import com.ecom.backend.payload.OrderRequest;
import com.ecom.backend.product.exception.ResourceNotFoundException;
import com.ecom.backend.user.model.User;
import com.ecom.backend.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderReop;

    //order Create method

    public OrderDto orderCreate(OrderRequest request, String username) {

        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User not found"));

        String cartId = request.getCartId();
        String orderAddress=request.getOrderAddress();
        //find cart
        Cart cart= this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart Not Found"));
        //getting CartItem
        Set<CartItem> items  = cart.getItems();
        Order order=new Order();

        AtomicReference<Double> totalOrderPrice= new AtomicReference<Double>(0.0);
        Set<OrderItem>	orderItems=items.stream().map((cartItem)-> {
            OrderItem orderItem=new OrderItem();
            //set cartItem into OrderItem

            //set product in orderItem
            orderItem.setProduct(cartItem.getProduct());

            //set productQty in orderItem

            orderItem.setProductQuantity(cartItem.getQuantity());

            orderItem.setTotal_productPrice(cartItem.getTotal_price());
            orderItem.setOrder(order);

            totalOrderPrice.set(totalOrderPrice.get()+ orderItem.getTotal_productPrice());
            String productId=orderItem.getProduct().getId();

            return orderItem;
        }).collect(Collectors.toSet());

        order.setBillingAddress(orderAddress);
        order.setOrderDelivered(null);
        order.setOrderStatus("CREATED");
        order.setPaymentStatus("NOT PAID");
        order.setUser(user);
        order.setOrderItem(orderItems);
        order.setOrderAmount(totalOrderPrice.get());
        order.setOrderCreateAt(new Date());
        Order save;
        if(order.getOrderAmount()>0){
            save = this.orderReop.save(order);
            cart.getItems().clear();
            this.cartRepo.save(cart);
        }else {
            System.out.println(order.getOrderAmount());
            throw new ResourceNotFoundException("Please Add to Cart First then place Order");
        }


        return this.modelMapper.map(save,OrderDto.class);
    }
    public void cancelOrder(String orderId) {
       Order order = this.orderReop.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order not Found"));
       this.orderReop.delete(order);
    }

    public OrderDto findById(String orderId){
       Order order = this.orderReop.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order not Found"));
        return this.modelMapper.map(order,OrderDto.class);

    }
}
