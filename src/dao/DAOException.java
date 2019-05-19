package dao;

/**
 *
 * @author dinjo998
 */
public class DAOException extends RuntimeException {

	/**
	 * Creates a new instance of <code>DAOException</code> without detail
	 * message.
	 * @param reason
	 */
		public DAOException(String reason) {
			super(reason);
		}

		public DAOException(String reason, Throwable cause) {
			super(reason, cause);
		}
}
