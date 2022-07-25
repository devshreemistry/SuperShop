<%
    User user = (User) session.getAttribute("current_user");
    if (user == null) {
        session.setAttribute("message", "you are not logged in ");

        response.sendRedirect("login.jsp");

        return;

    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="components/common_css_js.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>checkout Page</title>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>


        <div class="container">
            <div class="row mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-3">your selected items</h3>
                            <div class="cart-body">


                            </div>

                        </div>

                    </div>

                </div>



                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-3">your Details</h3>

                            <form action="#">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                           <input type="email"  value="<%=user.getUserEmail()%>"class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div> 

                                <div class="form-group">
                                    <label for="name">Your Name</label>
                                    <input type="text" name= "name" value="<%=user.getUserName()%>" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter your name">

                                </div> 
                                <div class="form-group">
                                    <label for=" phone"> Phone no</label>
                                    <input name="user_phone" value="<%= user.getUserPhone()%>" type="number" class="form-control" id=" phone" aria-describedby="emailHelp" placeholder="Enter contact number">
                                </div>

                                <div class="form-group">
                                    <label for=" address">Shipping Address</label>
                                    <textarea id="address" name="user_address" value="<%= user.getUserAddress()%>" style="height:100px;"class="form-control" placeholder="Enter your address" ></textarea>
                                </div>

                                <div class="container text-center">
                                    <button onClick="createOrderId()" class="btn-outline-success">Order Now</button>  
                                </div>

                            </form>




                        </div>

                    </div>

                </div>

            </div>

        </div>
        <%@include file ="components/common_models.jsp"  %>  
        
    </body>
</html>
