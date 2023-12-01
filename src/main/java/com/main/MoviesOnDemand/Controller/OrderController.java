package com.main.MoviesOnDemand.Controller;

import com.main.MoviesOnDemand.Entity.Movie;
import com.main.MoviesOnDemand.Services.MovieService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.razorpay.*;

import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout"; // Assuming your checkout.html is in the "templates" directory
    }

    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
        float amount = Float.parseFloat(data.get("amount").toString());

        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_0SBsEE9mUsQgo2", "RIw3rzmgm6UcLJajhic1gEQM");

        JSONObject object = new JSONObject();
        object.put("amount", amount*100);
        object.put("currency", "INR");
        object.put("receipt", "txn_123456");

        Order order = razorpayClient.orders.create(object);

        return order.toString();
    }
}
