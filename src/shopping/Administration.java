package shopping;

import dao.JdbcConnection;
import dao.ProductDataInterface;
import dao.ProductDatabase;
import gui.MainMenu;

/**
 *
 * @author dinjo998
 */
public class Administration {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ProductDataInterface dao;
		dao = new ProductDatabase();
		MainMenu menu = new MainMenu(dao);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
}
