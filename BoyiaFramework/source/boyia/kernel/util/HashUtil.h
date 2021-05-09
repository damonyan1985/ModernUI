#ifndef HashUtil_h
#define HashUtil_h

#include "StringUtils.h"

namespace util {
class HashString {
public:
    HashString(const HashString& str, LBool deep = LTrue)
        : HashString(str.m_value, deep)
    {
    }

    HashString(const String& value, LBool deep = LTrue)
        : m_hash(StringUtils::hashCode(value))
    {
        Copy(value, deep);
    }

    LVoid Copy(const String& value, LBool deep = LTrue)
    {
        if (deep) {
            m_value = value;
        } else {
            m_value.Copy(value.GetBuffer(), LFalse, value.GetLength());
        }
    }

    bool operator==(const HashString& str1) const
    {
        if (m_hash != str1.m_hash) {
            return false;
        }

        return m_value.CompareCase(str1.m_value);
    }

    LUint hash() const
    {
        return m_hash;
    }

private:
    String m_value;
    LUint m_hash;
};

class HashPtr {
public:
    HashPtr(LUintPtr ptr)
        : m_ptr(ptr)
    {
    }

    bool operator==(const HashPtr& ptr) const
    {
        return m_ptr == ptr.m_ptr;
    }

    LUint hash() const
    {
        return m_ptr;
    }

private:
    LUintPtr m_ptr;
};
}

using util::HashString;
using util::HashPtr;
#endif // !HashUtil_h
