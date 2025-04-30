# SNMP Network Monitoring System  

A Java application for monitoring simulated routers in GNS3 using SNMP (v1/2c). Tracks interface statuses, traffic metrics, routing tables, and BGP sessions with real-time updates.  
> Note: Uses community strings si2019 (read/write).
## Features  
- **Variant 1**: Interface monitoring (status, MTU, speed)  
- **Variant 2**: Traffic flow analysis (packets/bitrate)  
- **Variant 3**: Routing table visualization  
- **Variant 4-5**: BGP neighbor/tracking  
- **Variant 6-9**: SNMP traps, CPU/memory monitoring, TCP/UDP session tracking  

## Tech Stack  
- **Java** + **SNMP4J API**  
- **GNS3** (network simulation)  
- **iReasoning MIB Browser** (debugging)  
