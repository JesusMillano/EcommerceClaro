package Core.utils.shadowDOM;

import Core.reporter.EstadoPrueba;
import Core.reporter.PdfBciReports;
import io.github.sukgu.Shadow;
import org.openqa.selenium.WebElement;


public class ShadowContext {
    private static ShadowManager shadowManager = new ShadowManager();
    private static WebElement tempWebElement;

    @Deprecated
    public ShadowContext(){}

    @Deprecated
    public static Shadow getShadowManager(){ return shadowManager.getShadowDOM(); }

    @Deprecated
    public static WebElement getElement(String cssSelector){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null){ PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el objeto.", EstadoPrueba.FAILED, false); }
        return tempWebElement;
    }

    @Deprecated
    public static void clickButton(String cssSelector){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el objeto a clickear.", EstadoPrueba.FAILED, true); }
    }

    @Deprecated
    public static void InText(String cssSelector, String inText){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el objeto.", EstadoPrueba.FAILED, true); }
        tempWebElement.click();
        tempWebElement.clear();
        tempWebElement.sendKeys(inText);
    }

    @Deprecated
    public static void validateText(String cssSelector, String compareText){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el objeto a comparar.", EstadoPrueba.FAILED, true); }

        if(tempWebElement.getText().compareTo(compareText) != 0) { PdfBciReports.addReport("Shadow DOM", "Los textos no coinciden.", EstadoPrueba.FAILED, true); }
        else{ PdfBciReports.addReport("Shadow DOM", "El textodel elemento coincide con el texto esperado.", EstadoPrueba.FAILED, true); }
    }

    @Deprecated
    private static void selectItemDropDownList(String cssSelector, String item){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar la lista.", EstadoPrueba.FAILED, true); }
        tempWebElement.getTagName();
        shadowManager.getShadowDOM().selectDropdown(tempWebElement, item);
    }

    @Deprecated
    private static void selectRadioButton(String cssSelector, String item){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el radio button.", EstadoPrueba.FAILED, true); }
        shadowManager.getShadowDOM().selectRadio(tempWebElement, item);
    }

    @Deprecated
    private static void selectCheckBox(String cssSelector, String item){
        tempWebElement = shadowManager.getShadowDOM().findElement(cssSelector);
        if(tempWebElement == null) { PdfBciReports.addReport("Shadow DOM", "No fue posible encontrar el check.", EstadoPrueba.FAILED, true); }
        shadowManager.getShadowDOM().selectCheckbox(tempWebElement, item);
    }
}