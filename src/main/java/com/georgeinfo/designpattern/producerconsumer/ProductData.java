package com.georgeinfo.designpattern.producerconsumer;

/**
 * 产品包装类（数据包装类）
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class ProductData<T> {
    private T product;

    public ProductData(T product) {
        this.product = product;
    }

    public T getProduct() {
        return product;
    }

    public void setProduct(T product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "产品{" + product + '}';
    }
}