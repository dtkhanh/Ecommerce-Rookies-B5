package com.example.ecommerce_rookies.TestService;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.exception.cart.NotFoundCart;
import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.OrderDetailsDTO;
import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.*;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.OderModelServicel;
import com.example.ecommerce_rookies.services.impl.OrderDetailsServicelmpl;
import com.example.ecommerce_rookies.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderDetailTest {
    private OrderDetailsRepository orderDetailsRepository;

    private AccountRepository accountRepository;

    private CartmodelRepository cartmodelRepository;

    private ProductRepository productRepository;
    private OrderDetailsServicelmpl orderDetailsServicelmpl;

    private OrderDetails orderDetails;
    private  Cartmodel cartmodel;
    private Product product;
    private OrderDetailsDTO orderDetailsDTO;

    @BeforeEach
    public void beforeEach() {
        productRepository = mock(ProductRepository.class);
        accountRepository = mock(AccountRepository.class);
        orderDetailsRepository = mock(OrderDetailsRepository.class);
        cartmodelRepository = mock(CartmodelRepository.class);
        orderDetailsServicelmpl = new OrderDetailsServicelmpl(orderDetailsRepository, accountRepository,cartmodelRepository,productRepository);
        orderDetails = mock(OrderDetails.class);
        orderDetailsDTO = mock(OrderDetailsDTO.class);
        cartmodel = mock(Cartmodel.class);
        product = mock(Product.class);

    }
    @Test
    public void getAllOderDetail_ReturnList_True() {
        List<OrderDetails> list = new ArrayList<OrderDetails>();
        list.add(orderDetails);
        list.add(orderDetails);
        when(orderDetailsRepository.findAll()).thenReturn(list);
        List<OrderDetails> result = orderDetailsServicelmpl.getAllOdersDetails();
        assertThat(result.size(), is(list.size()));
    }
    @Test
    public void Test_convert_OderDetailDTO_to_OderDetail_FoundException(){
        OrderDetails orderDetails1 = new OrderDetails();
        when(orderDetailsRepository.findById(1L)).thenReturn(Optional.of(orderDetails1));
        OrderDetailsDTO orderDetailsDTO1 = new OrderDetailsDTO(2,"1");
        when(cartmodelRepository.findById(1L)).thenReturn(Optional.of(cartmodel));
        when(productRepository.findById(Long.parseLong("1"))).thenReturn(Optional.empty());
        NotFoundProductByCategory.NotFoundProduct exception = Assertions.assertThrows(NotFoundProductByCategory.NotFoundProduct.class,
                () -> orderDetailsServicelmpl.convertOrderDetails(1L,orderDetailsDTO1));
        assertThat(exception.getMessage(), is("Can not find product with id1"));
    }

    @Test
    public void Test_convert_OderDetailDTO_to_OderDetail(){
        OrderDetails orderDetails1 = new OrderDetails();
        when(orderDetailsRepository.findById(1L)).thenReturn(Optional.of(orderDetails1));
        OrderDetailsDTO orderDetailsDTO1 = new OrderDetailsDTO(2,"1");
        when(cartmodelRepository.findById(1L)).thenReturn(Optional.of(cartmodel));
        when(productRepository.findById(Long.parseLong("1"))).thenReturn(Optional.of(product));
        orderDetails1 = orderDetailsServicelmpl.convertOrderDetails(1L,orderDetailsDTO1);
        assertEquals(orderDetails1.getProduct(),Optional.of(product).get());
    }

    @Test
    public void Test_getOrderDetail_ByTdAccount(){
        Long id=1L;
        Account account = mock(Account.class);
        Category category = mock(Category.class);
        Product product1 = new Product(10L,"",0,"",0,category,null,"",null,null,null);
        Cartmodel cartmodel1 = new Cartmodel(id,20,null,account,null,null);
        Cartmodel cartmodel2 = new Cartmodel(30L,20,null,account,null,null);
        OrderDetails orderDetails1 = new OrderDetails(2L,20,20,cartmodel1,product1);
        OrderDetails orderDetails2 = new OrderDetails(2L,20,20,cartmodel2,product1);
        List<OrderDetails> list = new ArrayList<OrderDetails>();
        List<OrderDetailsDTO> list1 = new ArrayList<OrderDetailsDTO>();
        list.add(orderDetails1);
        list.add(orderDetails1);
        list.add(orderDetails2);
        assertEquals(orderDetails1.getCartmodel().getId(),id);
        when(orderDetailsRepository.findAll()).thenReturn(list);
        list1 = orderDetailsServicelmpl.getOrderDetailByTdAccountIdProduct(1L);
        assertEquals(2,list1.size()); // do em đang test nếu có id khác nên em mới đưa id local nếu bỏ oderDetail2 thì oke ạ
    }

    @Test
    public void test_add_Product_to_Cart_NotException_Account(){
        Long id = 1L;
        Account account = mock(Account.class);
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        NotFoundAccount exception = Assertions.assertThrows(NotFoundAccount.class,
                () -> orderDetailsServicelmpl.addProductByCart(1L,orderDetailsDTO));
        assertThat(exception.getMessage(), is("Can not find account with id: 1"));
    }

}
