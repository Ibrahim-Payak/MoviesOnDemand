function addMovieToShoppingCart(e) {
  let movie = {
    movieId: e.getAttribute("movieId"),
    movieName: e.getAttribute("movieName"),
    moviePrice: e.getAttribute("moviePrice"),
    quantity: 1,
  };

  console.log(movie);

  localStorage.setItem("cart", JSON.stringify(movie));
}

function updateCart() {
  let ticket = `<h4 class="text-center">Number of tickets: <br>
                        <i class="bi bi-dash-circle text-success" onclick="decrementTicket()"></i>
                        <span id="ticketCount">${ticketCount}</span>
                        <i class="bi bi-plus-circle text-danger" onclick="incrementTicket()"></i>
                    </h4>`;
  $(".cart-body").html(ticket);
}

$(document).ready(function () {
  updateCart();
});

let ticketCount = 1;

// Function to increment the ticket count
function incrementTicket() {
  ticketCount++;
  updateTicketCount();
}

// Function to decrement the ticket count (with a minimum value of 1)
function decrementTicket() {
  if (ticketCount > 1) {
    ticketCount--;
    updateTicketCount();
  }
}

// Function to update the displayed ticket count
function updateTicketCount() {
  $("#ticketCount").text(ticketCount);

  let cart = JSON.parse(localStorage.getItem("cart"));

  // Update the quantity in cart
  cart.quantity = ticketCount;
  localStorage.setItem("cart", JSON.stringify(cart));
}

//checkout page
$(document).ready(function () {
  const bookingInfo = JSON.parse(localStorage.getItem("cart"));

  $(".name").text(bookingInfo.movieName);
  $(".quantity").text(bookingInfo.quantity);
  $(".total").text(`Rs. ${bookingInfo.quantity * bookingInfo.moviePrice}`);
});

const checkoutPage = () => {
  // Redirect to the checkout page
  location.href = "/checkout";
};

//req. to server to create order
const initPayment = () => {
  // Execute the code when the DOM is fully loaded on the page
  let amountText = $(".total").text();
  let amount = parseInt(amountText.substring(3));

  if (amount == "" || amount == null) {
    alert("amount is required");
    return;
  }

  //use ajax to send req. to server to create order

  $.ajax({
    url: "/createOrder",
    data: JSON.stringify({ amount: amount }),
    contentType: "application/json",
    type: "POST",
    dataType: "json",
    success: function (response) {
      //order is created
      if (response.status == "created") {
        // open payment form
        var options = {
          key: "rzp_test_0SBsEE9mUsQgo2",
          amount: response.amount,
          currency: "INR",
          name: "Movies On Demand",
          description: "Paying for movie tickets",
          image:
            "https://cdn.inc42.com/wp-content/uploads/2019/04/movie-ticketing.jpg",
          order_id: response.id,
          handler: function (response) {
            console.log(response.razorpay_payment_id);
            console.log(response.razorpay_order_id);
            console.log(response.razorpay_signature);
            alert("Congrats, payment successful");
          },
          prefill: {
            name: "",
            email: "",
            contact: "",
          },
          notes: {
            address: "Movies on Demands",
          },
          theme: {
            color: "#3399cc",
          },
        };

        var rzp = new Razorpay(options);
        rzp.on("payment.failed", function (response) {
          alert("opps, payment failed");
        });

        rzp.open();
      }
    },
    error: function (error) {
      alert("something went wrong..");
    },
  });
};
