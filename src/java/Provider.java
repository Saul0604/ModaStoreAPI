
package org.lasalle.modastore.model;

public class Provider {

    private int idProvider;
    private String providerName;
    private String phone;
    private String mainProduct;

    public Provider() {
    }

    public Provider(int idProvider, String providerName, String phone, String mainProduct) {
        this.idProvider = idProvider;
        this.providerName = providerName;
        this.phone = phone;
        this.mainProduct = mainProduct;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }
}

