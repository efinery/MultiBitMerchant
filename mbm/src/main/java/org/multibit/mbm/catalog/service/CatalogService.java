package org.multibit.mbm.catalog.service;

import org.multibit.mbm.catalog.dao.ItemDao;
import org.multibit.mbm.catalog.dto.Item;
import org.multibit.mbm.web.rest.v1.client.catalog.ItemPagedQueryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Service to provide the following to Controllers:</p>
 * <ul>
 * <li>Transactional collection of Item entries</li>
 * </ul>
 *
 * @since 1.0.0
 *         
 */
@Service
@Transactional(readOnly = true)
public class CatalogService {

  @Resource(name = "hibernateItemDao")
  private ItemDao itemDao;

  /**
   * Attempts to locate an Item based on the given SKU (Stock-keeping unit)
   * @param sku The SKU for the Item
   * @return An Item or null if not found
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Item getBySKU(String sku) {
    return itemDao.getBySKU(sku);
  }

  /**
   * Attempts to locate an Item based on the given GTIN (Global Trade Identification Number)
   * @param gtin The GTIN for the Item
   * @return An Item or null if not found
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Item getByGTIN(String gtin) {
    return itemDao.getByGTIN(gtin);
  }

  /**
   * Attempts return a list of Items
   * @return An Item or null if not found
   * @param itemPagedQueryResponse The query parameters
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public List<Item> getPagedItems(ItemPagedQueryResponse itemPagedQueryResponse) {
    return itemDao.getPagedItems(itemPagedQueryResponse);
  }

  /**
   *
   * @param id The primary key of the item
   * @return The matching Item (if it exists)
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Item getById(Long id) {
    return itemDao.getById(id);
  }

  /**
   * Package local to allow testing
   * @return The Item DAO
   */
  ItemDao getItemDao() {
    return itemDao;
  }

  public void setItemDao(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

}
