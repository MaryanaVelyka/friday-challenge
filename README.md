# Friday QA Candidate Assigment

## Sales funnel

#### Selected technologies    
| Technology    | Stack                         |
|---------------| :-----------------------------|
| Build tool    |  Maven|
| Language      |  Java|   
| UI technology |  Selenium|
| Test framework|  TestNG |

### Implementation
The test framework has set of Page Object(s) implementation. There is one test `InsuranceWizardTest`. It picks three different car brands, each brand with three different cars.

The last test (it's commented because `jacoco` report is not generated if build is failed) for FORD, Edge is failing in order to show screenshots taking capability.

* Screenshots for failing tests are located in `./screenshots` folder.
* Jacoco html report is generated after tests execution and are located in `./target/jacoco-ut/index.html`

### Running the tests
Run `mvn clean test` from the root folder. 
Default browser for test execution is Chrome.

To execute test in Firefox browser, please pass VM option `-Dbrowser=firefox`


