package net.houselease.service;

import java.util.List;

import net.houselease.pojo.Applyout;
import net.houselease.pojo.Checkout;
import net.houselease.pojo.Zulist;

public interface CheckoutService {
public void insertcheckout(Checkout checkout);
public List<Checkout> getallcheckout();
public void deletecheckout(Integer id);
}
