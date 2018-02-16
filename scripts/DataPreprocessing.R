#!/usr/bin/Rscript

f <- file("stdin")
open(f)

data <- read.table(f, header=TRUE, sep=",")

# Normalisierung
processed_data = scale(data)

write.table(processed_data, stdout(), sep = ",", eol = "\n", row.names = FALSE)