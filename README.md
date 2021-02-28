# Nature Remo IPアドレス取得方法
1. Instance Name取得
```
% dns-sd -B _remo._tcp
Browsing for _remo._tcp
DATE: ---Sun 28 Feb 2021---
21:12:14.374  ...STARTING...
Timestamp     A/R    Flags  if Domain               Service Type         Instance Name
21:12:14.532  Add        2   4 local.               _remo._tcp.          Remo-9D3182
```

1. ローカルIPアドレス取得
```
% dns-sd -G v4 Remo-9D3182.local
DATE: ---Sun 28 Feb 2021---
21:12:47.744  ...STARTING...
Timestamp     A/R    Flags if Hostname                               Address                                      TTL
21:12:47.746  Add 40000002  4 Remo-9D3182.local.                     192.168.11.2                                 120
21:32:21.528  Rmv        0  4 Remo-9D3182.local.                     192.168.11.2                                 0
21:32:27.980  Add        2  4 Remo-9D3182.local.                     192.168.11.2                                 120
21:33:13.131  Rmv        0  4 Remo-9D3182.local.                     192.168.11.2                                 0
22:02:23.542  Add        2  4 Remo-9D3182.local.                     192.168.11.2                                 120
``` 