<%@page import="com.mycompany.dapp.helper.Helper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.dapp.entities.Category"%>
<%@page import="com.mycompany.dapp.Dao.CategoryDao"%>
<%@page import="com.mycompany.dapp.helper.FactoryProvider"%>
<%@page import="com.mycompany.dapp.entities.User"%>
<%

    User user = (User) session.getAttribute("current_user");
    if (user == null) {
        session.setAttribute("message", "you are not logged in ");

        response.sendRedirect("login.jsp");

        return;

    } else {
        if (user.getUserType().equals("normal")) {

            session.setAttribute("message", "you cant access this page.this is accesible for admin");

            response.sendRedirect("login.jsp");
            return;

        }

    }


%>

 <%
                                CategoryDao catDao = new CategoryDao(FactoryProvider.getFactory());
                                List<Category> list = catDao.getCategory();
                          

 Map<String,Long> m = Helper.getCount(FactoryProvider.getFactory());
 
 %>







<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>

        <div class="container admin">
            <div class="container-fluid mt-3">

                <%@include file="components/message.jsp" %>
            </div>

            <div class="row mt-3">
                <div class="col-md-4">
                    <div class="card">

                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 120px;" class="img-fluid rounded-circle" src="images/man.png"alt="user_icon">
                            </div>
                            <h2><%= m.get("userCount")%></h2>
                            <h2 class="text-uppercase text-muted">User</h2> 

                        </div>
                    </div>  
                </div>

                <div class="col-md-4">

                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 120px;" class="img-fluid rounded-circle" src="images/bullets.png"alt="user_icon">
                            </div>
                            <h2><%= list.size()%></h2>
                            <h2 class="text-uppercase text-muted">Categories</h2> 

                        </div>
                    </div>   
                </div>

                <div class="col-md-4">


                    <div class="card">
                        <div class="card-body text-center">

                            <div class="container">    
                                <img style="max-width: 120px;" class="img-fluid rounded-circle" src="images/delivery-box.png" alt="user_icon">
                            </div>
                            <h2><%= m.get("productCount")%></h2>
                            <h2 class="text-uppercase text-muted">Products</h2> 
                        </div>

                    </div>   
                </div>

            </div>

            <div class="row mt-3">
                <div class="col-md-6">
                    <div class ="card " data-toggle="modal" data-target="#add-category-modal">
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 120px;" class="img-fluid rounded-circle" src="images/add.png" alt="user_icon">
                            </div>
                            <p class="mt-2">TO add new category click here</p>
                            <h2 class="text-uppercase text-muted">add category</h2>   

                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class ="card "data-toggle="modal" data-target="#add-product-modal" >
                        <div class="card-body text-center">
                            <div class="container">
                                <img style="max-width: 120px;" class="img-fluid rounded-circle" src="images/plus.png" alt="user_icon">
                            </div>
                            <p class="mt-2">TO add new products click here</p>
                            <h2 class="text-uppercase text-muted">add product</h2>   

                        </div>      
                    </div>
                </div>

            </div>

        </div>






        <!-- Modal -->
        <div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form action="ProductOperationServlet" method="post">
                            <input type="hidden" name="operation" value="addcategory">
                            <div class="form-group">

                                <input type="text" class="form-control" name="catTitle" placeholder="Enter category title here"required />
                            </div>
                            <div class="form-group">
                                <textarea style="height: 300px;" class="form-control" name="catDescription" placeholder="Add categoty description" ></textarea>

                            </div>
                            <div class="container text-center">

                                <button  class="btn btn-outline-success" >Add category</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>

        <!--starts add product-->  
        <div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Add Product Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="operation" value="addproduct">

                            <div class="form-group">

                                <input type="text" class="form-control" name="pTitle" placeholder="Enter product title here"required />
                            </div>
                            <div class="form-group">
                                <textarea style="height: 200px;" class="form-control" name="pDescription" placeholder="Add product description" ></textarea>

                            </div>

                            <div class="form-group">

                                <input type="number" class="form-control" name="pPrice" placeholder="Enter product price"required />
                            </div>

                            <div class="form-group">

                                <input type="number" class="form-control" name="pDiscount" placeholder="Enter discount on product"required />
                            </div>
                            <div class="form-group">

                                <input type="number" class="form-control" name="pQuantity" placeholder="Enter  product quantity"required />
                            </div>



                           
                            <div class="form-group">
 
                                <select name="catId" class="form-control" class="form-select" aria-label="Default select example">
                                 <option selected>Select category</option>   
                                <%
                                    for (Category c : list) {

                                %> 
                                    <option value="<%=c.getCategoryId()%>">  <%=c.getCategoryTitle()%>        </option>
                                    <%}%>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="ppic">select picture of product</label>
                                <input type="file" class="form-control" id="ppic" name="ppic" required />
                            </div>


                            <div class="container text-center">

                                <button  class="btn btn-outline-success" >Add Product</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>

                        </form>

                    </div>

                </div>
            </div>
        </div>

        <!--end add product-->











   <%@include file ="components/common_models.jsp"  %>     

    </body>
</html>
