//import styles from "./css/style.css";

var newstrUser
function selectNum() {
    var strUser = document.getElementById("numberToSelect").value;
    alert(strUser);
    newstrUser = parseInt(strUser)
    console.log(newstrUser);
}

let i = [];

const cartItemsEl = document.querySelector(".number");

function add_to_cart(pid, pname, price) {
    let cart = localStorage.getItem("cart");
    if (cart == null) {
        //empty array
        let products = [];
        //create object
        let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price}
        products.push(product);
        //update products in localStorage object
        localStorage.setItem("cart", JSON.stringify(products));
        showToast("item is added to cart")

    } else {
        //cart is already presents
        //pcart is array 
        //we take cart and convert it to javascript array beacouse cart is in string form 
        let pcart = JSON.parse(cart);
        // let pcart=Jason.parse ( JSON.stringify(cart))
        let oldProduct = pcart.find((item) => item.productId == pid)
        if (oldProduct) {

            //we have to increase the Quantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1
            pcart.map((item) => {
                if (item.ProductId == oldProduct.productId)
                {
                    item.productQuantity = oldProduct.productQuantity;
                }

            })

            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast(oldProduct.productName + "quantity is increased ,quantity=" + oldProduct.productQuantity)

        } else {
            //we have to add the product
            let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price}
            pcart.push(product);
            //update products in localStorage object
            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast("product is added to cart")


        }

    }


    updateCart();


}



let totalPrice;

let cart = [];
function updateCart()
{

    //stringCart is in string form and cart is an object .we get cart object after parsing stringCart.
    let stringCart = localStorage.getItem("cart");
    cart = JSON.parse(stringCart);
    if (cart == null && cart.length == 0) {
        console.log("cart is empty");
        $(".cart-items").html(("0"));
        $(".cart-body").html("<h3>card does not have any items</h3>")
        $(".checkout-btn").attr('disabled', true)

    } else {

        console.log(cart);

        //`` back take it is an multiline string in which we can write so many strings
        $(".cart-items").html(`(${cart.length})`);
        let table = `
       <table class='table'>
        <thead class='thead-light'>
        <tr>
        <th>Item Name</th>
        <th>productprice</th>
        <th>Quantity</th>
         <th>Total Price</th>
        <th>Actiong</th>
      

        
        </tr>
        </thead>
        

`;

        totalPrice = 0;
        cart.map((item) => {


            table +=
                    ` <tr>  
           <td> ${item.productName} </td>
            <td>${item.productPrice}</td>
          
           <td> <div class="quantity">
              <button class="btn minus" id="decrement"  onClick="changeNumberOfUnits('minus',${item.productId})">-</button>
                  <div class="number" >${item.productQuantity}</div>
                  <button class="btn plus" id="increment"  onClick="changeNumberOfUnits('plus',${item.productId})">+</button> 
           </div>   </td>

             <td>${ item.productQuantity * item.productPrice}</td>
           
            <td><button onClick='deleteItemFromCart(${item.productId})'>Remove</button></td>
            
            </tr>
            `

            totalPrice += item.productPrice * item.productQuantity;
            // i.push(item.productId);  

        })

        table = table + `   
                <tr>  <td colspan='5' class='text-right id="totalprice"  font-weight-bold m-5'> Total Price:${totalPrice}</td>   </tr>
               
                </table>`
        $(".cart-body").html(table);
        $(".checkout-btn").attr('disabled', false)
        // console.log("removed")

//console.log(i);   

        console.log(totalPrice);
    }

}

function changeNumberOfUnits(action, id) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let oldProduct = cart.find((item) => item.productId == id)
    console.log(oldProduct.productId);
    if (oldProduct) {
        cart.map((item) => {
            let    productQuantity = item.productQuantity;
            console.log(productQuantity);
            console.log(item.productId);
            console.log(action);

            if (item.productId == oldProduct.productId) {


                if (action === 'minus' && item.productQuantity > 1) {
                    oldProduct.productQuantity = oldProduct.productQuantity - 1
                    item.productQuantity = oldProduct.productQuantity;
                    //productQuantity--;
                    console.log(item.productQuantity);
                } else if (action === 'plus' && item.productQuantity <= 4) {

                    oldProduct.productQuantity = oldProduct.productQuantity + 1
                    item.productQuantity = oldProduct.productQuantity;
                    if (item.productQuantity == 5) {
                        alert("max 5 are allowed");
                    }

                }



            }


        });
        localStorage.setItem("cart", JSON.stringify(cart));
        updateCart();

    }

}






function deleteItemFromCart(pid) {

    let cart = JSON.parse(localStorage.getItem('cart'));
    let newcart = cart.filter((item) => item.productId != pid)
    // i.pop(item.productId!=pid)
    localStorage.setItem('cart', JSON.stringify(newcart))
    updateCart();
    showToast("item is removed from cart")

    console.log("id" + i);

}

$(document).ready(function () {

    updateCart()
let stringCart = localStorage.getItem("cart");
               //let pcart = JSON.parse(stringCart);
                console.log(stringCart);
//    let t = localStorage.getItem("cart");
//    let c = JSON.parse(t);
//
//
//    c.map((item) => {
//        i.push(item.productId);
//
//    });
//    console.log(i);
})


function showToast(content) {
    $("#toast").addClass("display")
    $("#toast").html(content);
    setTimeout(() => {

        $("#toast").removeClass("display");
    }, 1000);
}

function goToCheckout() {
    window.location = "checkout.jsp"


}

















var xhttp = new XMLHttpRequest();
var RazorpayOrderId;
function createOrderId() {

    xhttp.open("GET", "http://localhost:1010/dapp/orderServlet?amount=" + totalPrice, false);
    xhttp.send();
    RazorpayOrderId = xhttp.responseText;
    OpenCheckout();
    // pd();
}

//const paymentStart = () => {
//    console.log("Payment started..");
//    var amount = totalPrice;
//    console.log(amount);
//    if (amount == "" || amount == null) {
//        alert("amount is needed");
//        return;
//    }
//
//    $.ajax({
//        url: 'orderServlet',
//        data: JSON.stringify({amount: amount, info: 'order_request '}),
//        // data:JSON.stringify({amount:amount}),
//        contentType: 'application/json',
//        type: "POST",
//        dataType: "json",
//        success: function (response) {
//            console.log(response);
//            if (response.status == "created") {
function OpenCheckout() {


    var options = {
        "key": "rzp_test_l7oRc3ZHTPmvSG", // Enter the Key ID generated from the Dashboard
        "amount": "amount", // Amount is in currency subunits. Default currency isINR. Hence, 50000 refers to 50000 paise

        "currency": "INR",
        "name": "shope",
        "description": "Test Transaction",
        // "image": "https://brandmark.io/logo-rank/random/bp.png",
        "image": "https://previews.123rf.com/images/distrologo/distrologo1902/distrologo190200697/117609579-online-shop-logo-design-vector-icon-shopping-logo-design.jpg",

        "order_id": RazorpayOrderId, //This is a sample Order ID. Pass the`id` obtained in the response of Step 1

        "handler": function (response) {


          let stringCart = localStorage.getItem("cart");
             let para= JSON.stringify(stringCart)
                //console.log(pcart);
// jQuery.ajax({
//                type: "POST",
//                url: "CartData",
//                //dataType:'JSON'
//                data:JSON.stringify(stringCart)
// 
//                success: function (data) {
//                    alert("success");
//
//                }
//            });

            jQuery.ajax({
                type: "POST",
                url: "PaymentData",
             
               data:"orderId=" + response.razorpay_order_id + "&amount=" + totalPrice + "&paymentId=" + response.razorpay_payment_id,
 
                success: function (data) {
                    alert("success");

                }
            });

            alert(response.razorpay_payment_id);
            alert(response.razorpay_order_id);
            alert(response.razorpay_signature)
        },
        "prefill": {
            "name": "",
            "email": "",
            "contact": ""
        },
        "notes": {
            "address": "Razorpay Corporate Office"
        },

        "theme": {
            "color": "#3399cc"
        }
    };


    var rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response) {
        alert(response.error.code);
        alert(response.error.description);
        alert(response.error.source);
        alert(response.error.step);
        alert(response.error.reason);
        alert(response.error.metadata.order_id);
        alert(response.error.metadata.payment_id);
        console.log("oops something went wrong");
    });
    rzp1.open();
}
//   },
//       error: function (error) {
//
//            console.log(error)
//            alert("somthing went wrong");
//        },
//   // });
//
//
//};


//function pd(){
//    
//    xhttp.open("POST","http://localhost:1010/dapp/PaymentData",false);
//   xhttp.setRequestHandler("Content-Type", "application/x-www-form-urlencoded");
//    xhttp.send("amount=totalPrice&orderId=10");
//   
//}