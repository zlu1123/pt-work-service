package com.lsg.common;
import java.util.HashSet;
import java.util.Set;


/**
 * @deccription
 * @author congnan.hua
 * @date 2016年12月8日
 */
public class OrderNumberGeneratorClient {

    final IdWorker idWorker = new IdWorker(0, 0);


    /***
     * 获取一批订单号供应用使用
     * 
     * @param batchLength
     *            批量数量
     * @param charLength
     *            订单号长度
     * @return
     */
    public synchronized Set<String> getOrderNumberByBatch(int batchLength, int charLength) {
        Set<String> ids = new HashSet<String>();
        try {
            for (int i = 0; i < batchLength; i++) {
                ids.add(idWorker.nextId(charLength));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }


    public synchronized String getSerialOrderNumber(int charLength) {
        String orderNum = idWorker.nextId(charLength);
        return orderNum;
    }


    public static void main(String[] args) {

        OrderNumberGeneratorClient worker = new OrderNumberGeneratorClient();

        Set<String> ids = worker.getOrderNumberByBatch(1000000, 8);
        int i = 1;
        for (String id : ids) {
            System.out.println(id + "=== " + i++);
        }
        System.out.println(" ids=== " + ids.size());
        String orderNum = worker.getSerialOrderNumber(8);
        System.out.println("orderNum: " + orderNum);
    }
}
