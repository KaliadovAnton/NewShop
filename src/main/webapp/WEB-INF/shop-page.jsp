<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@page import="com.mmalaenko.model.Product"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Shop</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="bootstrap.css">
        <link rel="stylesheet" href="style.css">
        <style>
            <%@ include file="style.css" %>
        </style>
    </head>

    <body>

        <form action="/cart" method="POST">
            <span>Hello, ${userName} </span> <br/>

                <c:if test="${cart!=null}">
                <span>You have already chosen:</span>
                    <ol>
                    <c:forEach  var="product"  items="${cart}" >

                          <li>${product}$</li>

                    </c:forEach>
                    </ol>
                </c:if>

            <span>Make your order: </span><br/>
            <select name="currentSelect"  size="2" >
                <option disabled>Select product</option>

               <c:forEach  var="product"  items="${productList}" >
                          <option value="${product}">${product}$</option>
               </c:forEach>

            </select><br/>
            <button formaction="/shop">Add item</button>
            <button type="submit">Submit</button>
        </form>
    </body>



</html>
