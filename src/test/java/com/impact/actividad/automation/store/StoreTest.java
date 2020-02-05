package com.impact.actividad.automation.store;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.asserts.Assertion;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Store.class)
public class StoreTest {
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
        Assert.assertEquals("Product code is obligatory.", msg);
    }

    @Test
    public void testNameObligatory() {
        Store store = new Store();
        String msg = store.addNewProduct("code", null, 152.0);
        Assert.assertEquals("Product name is obligatory.", msg);
    }

    @Test
    public void testPriceObligatory() {
        Store store = new Store();
        String msg = store.addNewProduct("nome", "name-1", null);
        Assert.assertEquals("Product price is obligatory.", msg);
    }

    @Test
    public void testMock() throws Exception {
        Store store = new Store();
        final String METHOD = "addNewProduct";

        Store spy = PowerMockito.spy(store);
        PowerMockito.when(spy, METHOD).thenReturn(90.00);
        double valor = 10.0;
        double res = spy.addNewProduct("code", "name-1", 12.34);

        Assert.assertEquals(100, res, 0);
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("getSaldo");
    }
}
