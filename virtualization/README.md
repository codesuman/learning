# :brain: What’s a “VM” anyway ?
VM stands for Virtual Machine — it's like a computer inside a computer. It runs its own OS, but it's actually just a guest running on a host machine using virtualization.

### :white_check_mark: So what are your colleagues referring to when they say "VM"?
They usually mean:

> “I’m working on a Linux system that’s running inside a virtual machine — like in VMware, VirtualBox, Hyper-V, or on a cloud like AWS, Azure, GCP.”

So when they say “SSH into my VM,” they’re connecting to this virtual Linux instance that’s:

* Isolated from their main OS

* Has its own filesystem, users, network

* But is actually not a real separate physical computer

### :question: How do you see it when you SSH?
When you SSH, you just get a terminal session into a Linux box — no matter whether it's:

A bare-metal Linux server (real physical machine),

A VM (virtualized),

A container (like Docker or K8s),

Or even a cloud instance (e.g., EC2 on AWS).

So they all “feel” the same when you SSH — it’s just a Linux prompt. But what’s under the hood is different.

### :mag: How to check if you're on a VM?

If you're curious whether you're inside a VM or not, try this:

```bash
systemd-detect-virt
```

#### Output could be:

* kvm, vmware, hyperv, etc. → you're inside a VM

* none → physical machine (or possibly container)

You can also check:

```bash
dmidecode -s system-product-name
```

* (Needs sudo — often shows "VirtualBox", "VMware", etc.)