#Makefile
help:
	@./build/install/app/bin/app -h 
run:
	@./build/install/app/bin/app file1.json file2.json
build:
	@gradle clean build
test:
	@gradle test
lint:
	@gradle checkstyleMain

.PHONY: build
