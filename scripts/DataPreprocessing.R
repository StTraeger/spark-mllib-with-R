#!/usr/bin/Rscript

# Class to map file -> PopulationEntry
setClass("PopulationEntry", slots = list(year = "numeric", population = "numeric"))


f <- file("stdin")
open(f)

# Mapping 

data <- read.table(f, header=TRUE, sep=",")

# Normalisierung
processed_data = scale(data)

write.table(processed_data, stdout(), sep = ",", eol = "\n", row.names = FALSE)