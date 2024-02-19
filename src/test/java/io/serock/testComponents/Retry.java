package io.serock.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    // если мы retry'им 3 раза, то первые два результата будут помечены skip/игнором а последний настоящий результат.

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) { // result = true? retry if false stop retrying process
        if(count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}
