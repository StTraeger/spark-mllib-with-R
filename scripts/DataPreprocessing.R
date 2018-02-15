#!/usr/bin/RScript

f <- file("stdin")
open(f)

data <- read.table(f, header=TRUE, sep=",")

# Normalisierung
processed_data <- scale(data)

# Check
colMeans(processed_data)

# Standardisierung nicht notwendig, da Daten nicht normalverteilt

write.table(processed_data, stdout(), sep = ",", eol = "\n", row.names = FALSE)