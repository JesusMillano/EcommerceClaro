package Core.utils.shadowDOM;
import Core.driver.DriverContext;
import io.github.sukgu.Shadow;

public class ShadowManager {
    private Shadow shadowDOM;

    @Deprecated
    public ShadowManager(){ shadowDOM = new Shadow(DriverContext.getDriver());}

    @Deprecated
    protected Shadow getShadowDOM(){ return shadowDOM; }
}