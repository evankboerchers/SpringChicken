package com.springchicken.analytics;

/**
 * Keys available for analytics logging.
 */
public final class AnalyticsKeys
{
    public static final String APPLICATION = "Application";
    public static final String COMPONENT = "Component";
    public static final String WELL_GUID = "WellId";
    public static final String RIG_GUID = "RigId";
    public static final String USER_ID = "UserId";
    public static final String URL = "Url";
    public static final String PATH = "Path";
    public static final String IP_ADDRESS = "Ip";
    public static final String REMOTE_HOSTNAME = "RemoteHostname";
    public static final String ORIGINAL_METHOD = "OriginalMethod";
    public static final String SESSION = "Session";
    public static final String REQUEST_ID = "RequestId";
    public static final String TESTING = "Testing";

    // Utility classes should not have a public or default constructor
    private AnalyticsKeys()
    {
    }
}
