   public enum Status{
        PROCESSING(0),
        UPDATING(1),
        DELETING(2),
        CREATING_FILES(3),
        RETRIEVING_DATA(4),
        RUNNING(5);

        private final int value;

        Status(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }