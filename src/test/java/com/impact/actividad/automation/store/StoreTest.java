package com.impact.actividad.automation.store;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Store.class)
public class StoreTest {

    private static String CODE_OBLIGATORY = "Product code is obligatory.";
    private static String NAME_OBLIGATORY = "Product name is obligatory.";
    private static String PRICE_OBLIGATORY = "Product price is obligatory.";


    @Test
    public void testAddNewProducts() {
        Store store = new Store();
        store.addNewProduct("code-1", "name-1", 152.0);
        store.addNewProduct("code-2", "name-2", 202.0);

        Assert.assertEquals(2, store.getProducts().size());
    }

    @Test
    public void testCodeObligatory() {
        Store store = new Store();
        String msg = store.addNewProduct(null, "name-1", 152.0);
        Assert.assertEquals(CODE_OBLIGATORY, msg);
    }

    @Test
    public void testNameObligatory() {
        Store store = new Store();
        String msg = store.addNewProduct("code", null, 152.0);
        Assert.assertEquals(NAME_OBLIGATORY, msg);
    }

    @Test
    public void testPriceObligatory() {
        Store store = new Store();
        String msg = store.addNewProduct("code", "name-1", null);
        Assert.assertEquals(PRICE_OBLIGATORY, msg);
    }

    @Test
    public void testMock() throws Exception {
        Store store = new Store();
        final String METHOD = "getProducts";

        Store spy = PowerMockito.spy(store);
        PowerMockito.when(spy, METHOD).thenReturn(new HashMap<String, Product>());

        Map<String, Product> msg = spy.getProducts();

        Assert.assertNotNull(msg);
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("getProducts");
    }

    @Test
    public void testProductAlreadyExist() {
        String code = "code";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 10.00);
        String msg = store.addNewProduct(code, "name-1", 20.00);
        Assert.assertEquals("Already exist a product with code " + code + ", please add other code.", msg);
    }

    @Test
    public void testAddProduct() {
        String code = "code";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.addExistProduct(code);
        Assert.assertEquals("The product was added.", msg);
    }

    @Test
    public void testAddProductCodeNotExist() {
        String code = "code";
        Store store = new Store();
        store.addNewProduct("produto", "name-1", 152.0);
        String msg = store.addExistProduct(code);
        Assert.assertEquals("Not exist product with code " + code + ", please first add New Product.", msg);
    }

    @Test
    public void testAddProductCodeObligatory() {
        Store store = new Store();
        String msg = store.addExistProduct("");
        Assert.assertEquals(CODE_OBLIGATORY, msg);
    }

    @Test
    public void testUpdateName() {
        String code = "code";
        String name = "updateName";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updateName(code, name);
        Assert.assertEquals("The name of the product with code " + code + " was update.", msg);
    }

    @Test
    public void testUpdateNameCodeObligatory() {
        String code = "code";
        String name = "updateName";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updateName("", name);
        Assert.assertEquals(CODE_OBLIGATORY, msg);
    }

    @Test
    public void testUpdateNameObligatoryName() {
        String code = "code";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updateName(code, null);
        Assert.assertEquals(NAME_OBLIGATORY, msg);
    }

    @Test
    public void testUpdateNameCodeNotExist() {
        String code = "code-1";
        Store store = new Store();
        store.addNewProduct("code", "name-1", 152.0);
        String msg = store.updateName(code, "name");
        Assert.assertEquals("Not exist product with code " + code + ", please first add New Product.", msg);
    }

    @Test
    public void testUpdatePrice() {
        String code = "code";
        Double price = 200.00;
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updatePrice(code, price);
        Assert.assertEquals("The price of the product with code " + code + " was update.", msg);
    }

    @Test
    public void testUpdatePriceCodeObligatory() {
        String code = "code";
        Double price = 200.00;
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updatePrice("", price);
        Assert.assertEquals(CODE_OBLIGATORY, msg);
    }

    @Test
    public void testUpdatePriceObligatoryPrice() {
        String code = "code";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        String msg = store.updatePrice(code, null);
        Assert.assertEquals(PRICE_OBLIGATORY, msg);
    }

    @Test
    public void testUpdatePriceCodeNotExist() {
        String code = "code-1";
        Store store = new Store();
        store.addNewProduct("code", "name-1", 152.0);
        String msg = store.updatePrice(code, 100.00);
        Assert.assertEquals("Not exist product with code " + code + ", please first add New Product.", msg);
    }

    @Test
    public void testSellProduct() {
        String code = "code-1";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        store.addNewProduct("code-2", "name-2", 202.0);
        store.addExistProduct(code);
        store.addExistProduct(code);

        String msg = store.sellProduct(code, 2);

        Assert.assertEquals("Success Sell. You have 1 unity of this product in stock.", msg);
    }

    @Test
    public void testGetStockOfProduct() {
        String code = "code-1";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);
        store.addExistProduct(code);
        store.addExistProduct(code);
        Assert.assertEquals("3", store.getStockOfProduct(code));
    }

    @Test
    public void testGetProduct() {
        String code = "code-1";
        Store store = new Store();
        store.addNewProduct(code, "name-1", 152.0);

        Assert.assertNotNull(store.getProduct(code));
    }
}
