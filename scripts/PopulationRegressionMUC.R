#!/usr/bin/Rscript

f <- file("stdin")
open(f)

data <- read.table(f, header=TRUE, sep=",")

model <- lm(Einwohner ~ Jahr, data)
# Legt das trainierte Modell für die spätere Verwendung lokal ab.
saveRDS(model, "/home/straeger/Desktop/regression_model.rds")

png(filename = "/home/straeger/Desktop/regression_model.png")
plot(data)
abline(model, col = "blue")
dev.off()

write.table(data, stdout(), sep=",", eol="\n", row.names=FALSE)
