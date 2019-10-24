package pers.sanmu.proxy;

public class Producer implements IProducer {
    @Override
    public void saleProduct(float money) {
        System.out.println("销售产品，并拿到钱："+money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("提供售后服务，并拿到钱："+money);
    }
}
