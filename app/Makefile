#Makefile
help:
	@./build/install/app/bin/app -h 
run-json:
	@./build/install/app/bin/app file1.json file2.json
run-json-plain:
	@./build/install/app/bin/app -f plain file1.json file2.json
run-yml:
	@./build/install/app/bin/app file1.yml file2.yml
run-yml-plain:
	@./build/install/app/bin/app -f plain file1.yml file2.yml
run-json-json:
	@./build/install/app/bin/app -f json file1.json file2.json
build:
	@./gradlew clean installDist
test:
	@./gradlew test
lint:
	@./gradlew checkstyleMain checkstyleTest
report:
	@./gradlew jacocoTestReport

.PHONY: build
