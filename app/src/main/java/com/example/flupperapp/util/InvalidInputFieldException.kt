package com.example.flupperapp.util

/**
 * This class will be throw the exception
 * in our case, it is used to throw exception when there is any empty field value is getting
 */
class InvalidInputFieldException(message: String) : Exception(message)