# Tutorial with JUnit5 tests using Selenium Webdriver

[![build workflow](https://github.com/Xray-App/tutorial-java-junit5-selenium/actions/workflows/main.yml/badge.svg)](https://github.com/Xray-App/tutorial-java-junit5-selenium/actions/workflows/main.yml)
[![license](https://img.shields.io/badge/License-BSD%203--Clause-green.svg)](https://opensource.org/licenses/BSD-3-Clause)
[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/Xray-App/community)

## Overview

Code that supports the tutorial [Testing web applications using Selenium and JUnit5 in Java](https://docs.getxray.app/display/XRAY/Testing+web+applications+using+Selenium+and+Junit5+in+Java) showcasing the integration between [Xray Test Management](https://www.getxray.app/) on Jira and JUnit 5, using a custom report.

## Prerequisites

In order to run this tutorial, you need to have JDK 8 and Maven.
You also need to configure the GitHub packages maven repo, which requires authentication using GH username+token.
There's a [settings.yml.sample](settings.xml) where you need to set those credentials. Rename it to settings.xml and copy it to the `~/.m2` folder.

## Running

Tests can be run using the maven command `mvn`.

```bash
mvn test
```

Tests can also run inside a Docker container; local directory should be mounted so that HUnit XML results are stored locally.

```bash
docker build . -t tutorial_java_junit5_selenium
docker run --rm -v $(pwd)/reports:/source/reports -t tutorial_java_junit5_selenium
```

## Submitting results to Jira

Results can be submitted to Jira so that they can be shared with the team and their impacts be easily analysed.
This can be achieved using [Xray Test Management](https://www.getxray.app/) as shown in further detail in this [tutorial](https://docs.getxray.app/display/XRAY/Testing+web+applications+using+Selenium+and+Junit5+in+Java).

## Contact

Any questions related with this code, please raise issues in this GitHub project. Feel free to contribute and submit PR's.
For Xray specific questions, please contact [Xray's support team](https://jira.getxray.app/servicedesk/customer/portal/2).


## LICENSE

[BSD 3-Clause](LICENSE)
