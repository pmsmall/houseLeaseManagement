package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.Checkout;

public interface CheckoutService {
	public void insertcheckout(Checkout checkout);

	public List<Checkout> getallcheckout();

	public void deletecheckout(Integer id);
}
