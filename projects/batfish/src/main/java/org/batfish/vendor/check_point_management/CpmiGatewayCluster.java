package org.batfish.vendor.check_point_management;

import static com.google.common.base.Preconditions.checkArgument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.batfish.datamodel.Ip;

/** Data model for an object of type {@code CpmiGatewayCluster}. */
public final class CpmiGatewayCluster extends GatewayOrServer {

  @JsonCreator
  private static @Nonnull CpmiGatewayCluster create(
      @JsonProperty(PROP_IPV4_ADDRESS) @Nullable Ip ipv4Address,
      @JsonProperty(PROP_NAME) @Nullable String name,
      @JsonProperty(PROP_UID) @Nullable Uid uid) {
    checkArgument(ipv4Address != null, "Missing %s", PROP_IPV4_ADDRESS);
    checkArgument(name != null, "Missing %s", PROP_NAME);
    checkArgument(uid != null, "Missing %s", PROP_UID);
    return new CpmiGatewayCluster(ipv4Address, name, uid);
  }

  @VisibleForTesting
  CpmiGatewayCluster(Ip ipv4Address, String name, Uid uid) {
    super(ipv4Address, name, uid);
  }

  @Override
  public boolean equals(Object obj) {
    return baseEquals(obj);
  }

  @Override
  public int hashCode() {
    return baseHashcode();
  }
}