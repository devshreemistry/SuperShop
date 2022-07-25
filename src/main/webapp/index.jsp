<%@page import="com.mycompany.dapp.helper.Helper"%>
<%@page import="com.mycompany.dapp.entities.Products"%>
<%@page import="com.mycompany.dapp.Dao.ProductDao"%>
<%@page import="com.mycompany.dapp.Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.dapp.entities.Category"%>
<%@page import="com.mycompany.dapp.Dao.CategoryDao"%>
<%@page import="com.mycompany.dapp.helper.FactoryProvider" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%@include file="components/common_css_js.jsp" %>

    </head>
    <body>
        <%@include file="components/navbar.jsp" %>

        <div class ="row mt-3 mx-2">




            <%  String cat = request.getParameter("category");
                ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
                List<Products> plist = null;
                if (cat == null || cat.trim().equals("all")) {
                    plist = pdao.getAllProduct();
                } else {

                    int cid = Integer.parseInt(cat.trim());
                    plist = pdao.getAllProductsById(cid);
                }
                CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                List<Category> clist = cdao.getCategory();


            %>
            <div class="col-md-2 mt-4">
                <div class="list-group">
                    <a href="index.jsp?category=all" class="list-group-item list-group-item-action active">
                        All Products
                    </a>

                    <%                   for (Category c : clist) {


                    %>

                    <a href="index.jsp?category=<%= c.getCategoryId()%>" class="list-group-item list-group-item-action "><%= c.getCategoryTitle()%></a>
                    <%
                        }
                    %>




                </div> 
            </div>


            <div class="col-md-10 ">
                <div class="row mt-4">
                    <div class="col-md-12">
                        <div class="card-columns">



                            <%
                                for (Products p : plist) {

                            %>

                            <div class="card product-card ">


                                <div class="container text-center ">
                                    <img class="card-img-top" src="images/product/<%= p.getProductPhoto()%>" style ="max-height: 200px; max-width:100%; width: auto;" alt="Card image cap">
                                </div>
                                <div class="card-body ">     

                                    <h5 class="card-title"><%=p.getProductName()%></h5>
                                    <p class="card-text">
                                        <%=Helper.get10Words(p.getProductDescription())%>
                                    </p>

                                </div>



                                <div class="card-footer  "> 



                                    <button class="btn custom-bg text-white" onclick="add_to_cart(<%=p.getProductId()%>, '<%=p.getProductName()%>',<%=p.priceAfterDiscount()%>)">Add to card     </button>
                                    

                                   
                              


                                    <button class="btn btn-outline-success "> &#8377; <%=p.priceAfterDiscount()%> /- <span class="text-secondary discount-lable ">&#8377; <%=p.getProductPrice()%> , <%=p.getProductDiscount()%>%</span>  </button>

                                </div>
                            </div> 

                            <%
                                }
                                if (plist.size() == 0) {
                                    out.println("<h1> NO products in this category</h1>");

                                }

                            %> 


                        </div>
                    </div>  
                </div>
            </div>      

        </div> 
        <%@include file ="components/common_models.jsp"  %>        


    </body>
</html>
