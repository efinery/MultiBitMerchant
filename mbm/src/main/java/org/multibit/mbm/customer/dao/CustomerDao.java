package org.multibit.mbm.customer.dao;

import org.multibit.mbm.customer.dto.Customer;

public interface CustomerDao {

  /**
   * Attempt to locate the customer
   * @param openId The OpenId token
   * @return A matching Customer
   * @throws CustomerNotFoundException If something goes wrong
   */
  Customer getCustomerByOpenId(String openId) throws CustomerNotFoundException;

  /**
   * Persist the given Customer
   * @param customer A Customer (either new or updated)
   * @return The persisted Customer
   */
  Customer saveOrUpdate(Customer customer);

  /**
   * <p>Force an immediate in-transaction flush</p>
   * <p>Normally, this is only used in test code but must be on the interface to ensure
   * that injection works as expected</p>
   */
  void flush();

}
