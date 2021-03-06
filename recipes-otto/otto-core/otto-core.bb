DESCRIPTION = "The OTTO core software."
HOMEPAGE = "otto-project.github.io"

SRCREV = "9d0d0c8d23d67f9e42e5a1d348b55c58ddc7f75b"
SRC_URI = "https://github.com/OTTO-project/OTTO;branch=develop"
SRC_URI[md5sum] = "27474d23f02ee44fd0e9ffe3d72a60d9"
PV = "1.0.0+git${SRCPV}"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "glfw alsa-lib userland"
TOOLCHAIN = "clang"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DOTTO_BOARD=rpi-proto-1 -DOTTO_USE_LIBCXX=ON"

FILES_${PN} += "/home/root/otto/otto \
				/home/root/otto/data/* \
"

do_install_append () {
	install -d ${D}/home/root/otto
	install -m 0755 bin/otto ${D}/home/root/otto/
	install -d ${D}/home/root/otto/data
	cp -r ${S}/data/* ${D}/home/root/otto/data/
}
