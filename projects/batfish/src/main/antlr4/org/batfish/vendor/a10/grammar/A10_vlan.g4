parser grammar A10_vlan;

import A10_common;

options {
    tokenVocab = A10Lexer;
}

s_vlan: VLAN vlan_number newline sv_definition*;

sv_definition: svd_name | svd_router_interface | svd_tagged | svd_untagged;

svd_name: NAME vlan_name newline;

svd_router_interface: ROUTER_INTERFACE VE vlan_number newline;

svd_tagged: TAGGED vlan_iface_references newline;

svd_untagged: UNTAGGED vlan_iface_references newline;

vlan_iface_references: vlan_ifaces_range | vlan_ifaces_list;

// TODO support other types of interfaces, e.g. trunk
vlan_ifaces_range:
    // All(?) versions of ACOS
    vlan_iface_ethernet_range
;

// TODO support other types of interfaces, e.g. trunk
vlan_ifaces_list:
    // Only ACOS 2.X allows more than one interface here
    vlan_iface_ethernet+
;

vlan_iface_ethernet: ETHERNET num = ethernet_number;

vlan_iface_ethernet_range: ETHERNET num = ethernet_number TO to = ethernet_number;
