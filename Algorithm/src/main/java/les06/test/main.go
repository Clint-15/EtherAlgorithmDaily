package main

import "fmt"

func main() {
	a := 10

	rev := 0
	for a > rev {
		rev = rev*10 + a%10
		a /= 10
		fmt.Println(rev)
	}
}
