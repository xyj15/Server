package data.dataservice;

import po.ManagerPO;

/**
 * Created by apple on 2016/12/1.
 * @author  张新悦
 * @version 2016-12-01
 */
public interface ManagerDataService {
	public boolean updateManager(ManagerPO manager);
	public ManagerPO getManager();
	public void close();   //关闭输入流
}
