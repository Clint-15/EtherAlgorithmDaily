package main

import (
	"fmt"
	"sort"
)

type Person struct {
	Name string
	Age  int
}

type PersonSlice []Person

func (p PersonSlice) Len() int {
	return len(p)
}

func (p PersonSlice) Swap(i, j int) {
	p[i], p[j] = p[j], p[i]
}

func (p PersonSlice) Less(i, j int) bool {
	return p[i].Age < p[j].Age
}

func main() {
	person := []Person{
		{"2", 2},
		{"6", 6},
		{"3", 3},
		{"1", 1},
	}

	fmt.Println(person)

	sort.Sort(PersonSlice(person))
	fmt.Println(person)

	sort.Sort(sort.Reverse(PersonSlice(person)))

	fmt.Println(person)
	fmt.Println(person[0])
}
