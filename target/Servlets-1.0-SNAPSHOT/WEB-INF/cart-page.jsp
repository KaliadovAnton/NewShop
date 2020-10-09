<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mmalaenko.model.Product"%>

<!DOCTYPE html>

        <html>
           <head>
              <title>Cart</title>
              <meta charset="utf-8">
              <link rel="stylesheet" href="bootstrap.css"/>
              <link rel="stylesheet" href="style.css"/>
              <style>
                <%@ include file="style.css" %>
              </style>
           </head>
           <body>
              <div class="order">
              <span>Dear ${userName}, your order: </span> <br/>
              <table border="2">
                  <thead>
                    <tr>
                       <th>№</th>
                       <th>product</th>
                       <th>Price</th>
                    </tr>
                  </thead>
                  <%! private int i=1; %>
                  <c:forEach var="product" items="${bucket}">
                  <tr>
                     <td><%=i++%></td>
                     <td>${product.name}</td>
                     <td>${product.price} $</td>
                  </tr>
                  </c:forEach>
                  <tr>
                       <td></td>
                       <td>TOTAL:</td>
                       <td>${total} $</td>
                  </tr>

              </table>
               </div>
               <br/>
               <span>List of orders of ${userName}: </span><br/>

                <c:forEach  var="number"  items="${numbersOfOrders}" >

                    <form action="/cart" method"POST">
                        <button type="submit" value="${number}">${number}</button>
                    </form>


                </c:forEach>

           </body>
        </html>
