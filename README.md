# Advent of code collection

Advent of Code is an annual set of Christmas-themed computer programming challenges that follow an Advent calendar.

[![Java CI](https://github.com/jabrena/advent-of-code-collection/actions/workflows/maven.yml/badge.svg)](https://github.com/jabrena/advent-of-code-collection/actions/workflows/maven.yml)

## How to build in local?

```bash
sdk env install
./mvnw clean verify
./mvnw -pl 2024 clean verify -am
./mvnw -pl 2024 clean test -Dtest=Day14Test
./mvnw -pl 2024 clean dependency:tree
./mvnw -pl 2024 clean verify surefire-report:report -DshowSuccess=false
jwebserver -p 9000 -d "$(pwd)/2024/target/reports"

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
```

## References

### Libraries

- https://github.com/z669016/aoc
- https://github.com/z669016/algorithms
- https://javadoc.io/doc/com.google.guava/guava/latest/index.html
- https://github.com/dpaukov/combinatoricslib3
- https://github.com/sim642/adventofcode/tree/master/src/main/scala/eu/sim642/adventofcodelib
- https://github.com/bertjan/advent-of-code-2024/blob/main/src/main/java/utils/Matrix.java
- https://github.com/p-kovacs/advent-of-code-2024/tree/master/src/main/java/com/github/pkovacs/util

### Examples

- https://github.com/z669016/adventofcode-2022 (Java)
- https://github.com/forax/advent-of-code-2023 (Java)
- https://github.com/forax/advent-of-code-2024 (Java)
- https://github.com/nipafx/advent-of-code-2023 (Java)
- https://github.com/juan-medina/adventofcode2024 (C#)
- https://github.com/jmgimeno/aoc2024/tree/master (Scala)
- https://github.com/bertjan/advent-of-code-2024 (Java)
- https://github.com/neutropolis/aoc (APL)
- https://github.com/search?q=advent+of+code+2024++language%3AJava&type=repositories&l=Java&s=updated&o=desc

### Others

- https://adventofcode.com/
- https://adventofcode.com/2024
- https://adventofcode.com/2023
- https://adventofcode.com/2022
- https://adventofcode.com/2021
- https://adventofcode.com/2020
- https://adventofcode.com/2019
- https://adventofcode.com/2018
- https://adventofcode.com/2017
- https://adventofcode.com/2016
- https://adventofcode.com/2015
- https://www.reddit.com/r/adventofcode/
- https://www.reddit.com/r/adventofcode/?f=flair_name%3A%22Funny%22
- https://www.reddit.com/r/adventofcode/search/?q=flair_name%3A%22SOLUTION%20MEGATHREAD%22&restrict_sr=1

## Acknowledgements

Many thanks for ChatGPT and Gemini for the good converstations about Design thinking, Code refactoring seeking functional approaches in the morning (At 6:00AM) and the continuous conversations during the day with my friend [Juan Antonio Medina](https://www.github.com/juan-medina) and the Inspiration from [Rene van Putten](https://github.com/z669016/).

**Powered by Java 24**
