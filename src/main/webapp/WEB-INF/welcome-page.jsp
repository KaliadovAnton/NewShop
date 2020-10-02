<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
                <html>
                     <head>
                        <title>Welcome Page </title>
                        <meta charset="utf-8">
                        <link rel="stylesheet" href="style.css">
                        <style>
                           <%@ include file="style.css" %>
                        </style>
                    </head>
                    <body>

                        <form action="/shop" method="POST">
                        <span>Welcome to online-shop</span><br/>
                            <input name="userName" placeholder="Enter your name"><br/>
                            <input type="password" name="password" placeholder="Enter password"><br/>
                            <button type="submit">Enter</button> </br>
                             <input type="checkbox" name="check"><span>Accept a licensing agreement </span>
                        </form>
                    </body>
                </html>