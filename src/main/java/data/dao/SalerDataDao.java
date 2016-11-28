package data.dao;

import po.SalerPO;

public interface SalerDataDao {
	public boolean add(SalerPO saler);
	public boolean delete(SalerPO saler);
	public boolean update(SalerPO saler);
	public SalerPO getSaler(String ID);
}
