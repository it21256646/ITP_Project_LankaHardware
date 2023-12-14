<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OTP Verification</title>
</head>
<body>
    <h1>OTP Verification</h1>
    <p>A One-Time Password (OTP) has been sent to your email: ${param.email}</p>
    <form method="POST" action="verify-otp">
        <label>Enter
