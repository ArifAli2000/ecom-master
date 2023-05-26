package com.ecom.backend.cart.service;

import com.ecom.backend.user.model.User;
import com.ecom.backend.user.repository.UserRepository;
import com.ecom.backend.cart.dto.CartDto;
import com.ecom.backend.cart.model.Cart;
import com.ecom.backend.cart.model.CartItem;
import com.ecom.backend.cart.repository.CartRepository;
import com.ecom.backend.payload.ItemRequest;
import com.ecom.backend.product.exception.ResourceNotFoundException;
import com.ecom.backend.product.model.Product;
import com.ecom.backend.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ModelMapper modelMapper;

    public CartDto addItem(ItemRequest item, String username){
        String productId = item.getId();
        System.out.println(productId);
        int quantity=item.getQuantity();
        //fetch user
        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User not found"));

        //fetch Product
        Product product = this.productRepo.findById( productId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));

        //here we are checking product stock
        if(!product.isStock()){

            new ResourceNotFoundException("Product Out of Stock");
        }

        // create cartItem with productId and Qnt

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        double total_price = product.getProduct_price()*quantity;
        cartItem.setTotal_price(total_price);

        //getting cart from user
        Cart cart = user.getCart();

        if(cart==null) {
            cart=new Cart();
            //
            cart.setUser(user);
        }

        cartItem.setCart(cart);

        Set<CartItem> items = cart.getItems();

        // here we check item is available in CartItem or not
        // if CartItem is available + quantity else
        // add new product in cartItem

        AtomicReference<Boolean> flag=new AtomicReference<>(false);

        Set<CartItem> newproduct = items.stream().map((i)->{
            if(i.getProduct().getId()==product.getId()) {
                i.setQuantity(quantity);
                i.setTotal_price(total_price);
                flag.set(true);
            }
            return i;

        }).collect(Collectors.toSet());

        if(flag.get()){
            items.clear();
            items.addAll(newproduct);

        }else {
            cartItem.setCart(cart);
            items.add(cartItem);
        }

        Cart saveCart = this.cartRepo.save(cart);



        return  this.modelMapper.map(saveCart,CartDto.class);
        }

        public CartDto getAllCart(String email){
            // find user
           User user = this.userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
           //find cart
          Cart cart = this.cartRepo.findByUser(user).orElseThrow(() ->new ResourceNotFoundException("There is no cart"));
           return this.modelMapper.map(cart,CartDto.class);
        }

    // get cart by id

    public CartDto getCartById(String cartId){
//        User user = this.userRepo.findByEmail(username).orElseThrow(() ->new ResourceNotFoundException("User not found"));
       Cart findByUserAndCartId= this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        return this.modelMapper.map(findByUserAndCartId,CartDto.class);
    }

    public CartDto removeCartItemFromCart(String username, String ProductId){
        User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User Not found"));

        Cart cart=user.getCart();
        Set<CartItem> items = cart.getItems();

        boolean removeIf = items.removeIf((i)->i.getProduct().getId()==ProductId);
        Cart save = this.cartRepo.save(cart);
        System.out.println(removeIf);
        return this.modelMapper.map(save,CartDto.class);
    }


}