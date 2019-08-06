#!/bin/bash

sed \
  -e 's/org_name/cluster/g' \
  -e 's/Org Name/Cluster/g' \
  -e 's/space_name/namespace/g' \
  -e 's/Space Name/Namespace/g' \
  -e 's/app_name/job/g' \
  -e 's/cf_instance_id/pod/g' \
  -e 's/CF Instance ID/Pod/g' \
  -e 's/app_name/job/g' \
  micrometer-summary-cf.json 