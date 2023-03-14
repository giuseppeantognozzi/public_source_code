package contractsinfo;

class AddressNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	AddressNotFoundException(Long id) {
		super("Could not find address " + id);
	}
}
