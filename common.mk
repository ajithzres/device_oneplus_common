# Application
PRODUCT_PACKAGES += \
    OPSettings

# KeyHandler
PRODUCT_PACKAGES += \
    OPKeyHandler

PRODUCT_SYSTEM_SERVER_JARS += \
    OPKeyHandler

# Overlay
DEVICE_PACKAGE_OVERLAYS += \
    $(LOCAL_PATH)/overlay

# Disable dexopt for the KeyHandler
$(call add-product-dex-preopt-module-config, OPKeyHandler, disable)
