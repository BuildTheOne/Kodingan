#!/bin/bash
echo -n "Enter first number: "
read first
echo -n "Enter second number: "
read second
sum=$(($first + $second))
mul=$(($first * $second))
echo "Sum of $first and $second is $sum"
echo "Product of $first and $second is $mul"