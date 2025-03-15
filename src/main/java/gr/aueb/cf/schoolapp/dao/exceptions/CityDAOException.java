package gr.aueb.cf.schoolapp.dao.exceptions;

import java.io.Serial;

public class CityDAOException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public CityDAOException(String message) {
        super(message);
    }
}
